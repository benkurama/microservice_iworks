package com.iworks.reactspring.models;

import java.util.List;

public class GraphsModel {
    public String[] labels;
    public List<datasets> datasets;

    public GraphsModel() {
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public List<datasets> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<datasets> datasetsList) {
        this.datasets = datasetsList;
    }

    public static class datasets{
        public int[] data;
        public String[] backgroundColor;

        public datasets() {

        }

        public datasets(int[] data, String[] bg) {
            this.data = data;
            this.backgroundColor = bg;

        }


        public int[] getData() {
            return data;
        }

        public void setData(int[] data) {
            this.data = data;
        }

        public String[] getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String[] backgroundColor) {
            this.backgroundColor = backgroundColor;
        }
    }
}
