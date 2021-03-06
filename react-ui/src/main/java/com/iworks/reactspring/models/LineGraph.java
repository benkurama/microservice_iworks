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
        public boolean fill;
        public double tension;
        public String backgroundColor;

        public datasets() {

        }

        public datasets(String label, int[] data, String borderColor, String bg) {
            this.data = data;
            this.label = label;
            this.borderColor = borderColor;

            this.fill = false;
            this.tension=.4;
            this.backgroundColor = bg;
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

        public boolean isFill() {
            return fill;
        }

        public void setFill(boolean fill) {
            this.fill = fill;
        }

        public double getTension() {
            return tension;
        }

        public void setTension(double tension) {
            this.tension = tension;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }
    }
}
