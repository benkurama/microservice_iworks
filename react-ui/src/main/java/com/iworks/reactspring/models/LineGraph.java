package com.iworks.reactspring.models;

import java.util.List;

public class LineGraph {
    public String[] labels;
    public List<LineGraph.datasets> datasets;

    public LineGraph() {
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public List<LineGraph.datasets> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<LineGraph.datasets> datasets) {
        this.datasets = datasets;
    }

    public static class datasets{
        public int[] data;
        public String label;
        public String borderColor;

        public datasets() {
        }

        public datasets(String label, int[] data, String borderColor) {
            this.data = data;
            this.label = label;
            this.borderColor = borderColor;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int[] getData() {
            return data;
        }

        public void setData(int[] data) {
            this.data = data;
        }

        public String getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(String borderColor) {
            this.borderColor = borderColor;
        }
    }
}
