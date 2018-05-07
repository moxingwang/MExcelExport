package com.mxw.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MoXingwang on 2018-04-25.
 * 数据库有id主键，并且导出的数据不重复
 * 强制数据排序字段和id的方向一致
 */
public class ExportStrategy {

    private static final int DEFAULT_SECTION_LENGTH = 1000;
    private static final int MAX_LENGTH = 5000;

    private ExportQueryParam queryParam;
    private long sectionLength = 2000;
    private String sort;

    private ExportStrategy() {
    }

    public ExportStrategy(ExportQueryParam exportQueryParam, int sectionLength, String sort) {
        this.queryParam = exportQueryParam;
        this.sectionLength = sectionLength;
        this.sort = sort.toUpperCase();
    }

    public ExportResultDTO exportData(IExportInterface exportInterface) {
        //防止参数错误查询数据过大
        if (sectionLength > MAX_LENGTH) {
            throw new IllegalArgumentException("查询区间过大");
        }

        List<ExportDataSection> exportDataSections = new ArrayList<>();
        ExportResultDTO exportResultDTO = new ExportResultDTO(exportDataSections, -1L);

        ExportQueryDBParam exportQueryDBParam = new ExportQueryDBParam(0, 0, sort, sectionLength, queryParam.getParameter());

        //如果是第一次导出
        if (queryParam.getOrder() == 0) {
            ExportDataSection exportDataSectionHeader = new ExportDataSection();
            ExportDataSection exportDataSectionTail = new ExportDataSection();

            exportQueryDBParam.setKeyBegin(0);
            exportQueryDBParam.setKeyEnd(0);

            List<ExportData> exportDataListHeader = exportInterface.getData(exportQueryDBParam);
            if (null == exportDataListHeader || exportDataListHeader.isEmpty()) {
                return exportResultDTO;
            }

            exportDataSectionHeader.setOrder(0);

            if (exportDataListHeader.size() < sectionLength) {
                exportDataSectionHeader.setDataList(exportDataListHeader);
                exportDataSections.add(exportDataSectionHeader);
                return exportResultDTO;
            }

            //去除最后一个id的数据，防止mybatis查询一对多的情况,如果数据不重复就去掉这个步骤
            final Long headerLastId = exportDataListHeader.get(exportDataListHeader.size() - 1).getId();
            exportDataListHeader = exportDataListHeader.stream().filter(p -> !p.getId().equals(headerLastId)).collect(Collectors.toList());
            exportDataSectionHeader.setDataList(exportDataListHeader);
            exportDataSections.add(exportDataSectionHeader);

            exportQueryDBParam.setSort(revertSort());
            List<ExportData> exportDataListTail = exportInterface.getData(exportQueryDBParam);
            //反转顺序
            Collections.reverse(exportDataListTail);
            //去掉第一个id的数据
            final Long tailFirstId = exportDataListTail.get(0).getId();
            exportDataListTail = exportDataListTail.stream().filter(p -> !p.getId().equals(tailFirstId)).collect(Collectors.toList());
            //排查书是否重复
            if ("DESC".equals(sort) && tailFirstId > headerLastId) {
                exportDataListTail = exportDataListTail.stream().filter(p -> p.getId() <= headerLastId).collect(Collectors.toList());
            } else if ("ASC".equals(sort) && tailFirstId < headerLastId) {
                exportDataListTail = exportDataListTail.stream().filter(p -> p.getId() >= headerLastId).collect(Collectors.toList());
            } else {
                exportResultDTO.setNextOrder(exportDataListHeader.get(exportDataListHeader.size() - 1).getId());
            }
            exportDataSectionTail.setOrder(exportDataListTail.get(0).getId());
            exportDataSectionTail.setDataList(exportDataListTail);
            exportDataSections.add(exportDataSectionTail);
        } else {
            if ("DESC".equals(sort)) {
                exportQueryDBParam.setKeyEnd(queryParam.getOrder());
            } else {
                exportQueryDBParam.setKeyBegin(queryParam.getOrder());
            }
            List<ExportData> exportDataList = exportInterface.getData(exportQueryDBParam);

            if (null != exportDataList && !exportDataList.isEmpty()) {
                if (exportDataList.size() >= sectionLength) {
                    //去掉第一个id的数据
                    final Long lastId = exportDataList.get(0).getId();
                    exportDataList = exportDataList.stream().filter(p -> !p.getId().equals(lastId)).collect(Collectors.toList());
                }
                exportResultDTO.setNextOrder(exportDataList.get(exportDataList.size() - 1).getId());
            } else {
                exportResultDTO.setNextOrder(-1L);
            }
            ExportDataSection exportDataSection = new ExportDataSection(exportDataList, queryParam.getOrder());

            exportDataSections.add(exportDataSection);
        }

        return exportResultDTO;
    }

    private String revertSort() {
        return "ASC".equals(sort) ? "DESC" : "ASC";
    }

}
