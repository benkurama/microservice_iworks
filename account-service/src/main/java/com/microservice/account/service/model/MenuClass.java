package com.microservice.account.service.model;

import java.util.List;

public class MenuClass {
    public String label;
    public List<items> items;

    public static class items{
        public String label;
        public String icon;
        public String to;

        public items(String label, String icon, String to) {
            this.label = label;
            this.icon = icon;
            this.to = to;
        }
    }
}
