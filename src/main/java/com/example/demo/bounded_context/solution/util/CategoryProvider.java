package com.example.demo.bounded_context.solution.util;

import java.util.Map;

public class CategoryProvider {
    public static final Map<String, String> categoryMapper =
            Map.ofEntries(
                    Map.entry("paper", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/73222c73-1papper.png"),
                    Map.entry("can", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/9fd72933-2can.png"),
                    Map.entry("pet", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/9fd72933-2can.png"),
                    Map.entry("plastic", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/e05057be-aplastic.png"),
                    Map.entry("styrofoam", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/0fa9ba71-7styrofoam.png"),
                    Map.entry("plastic_bag", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/d5e1d263-cplastic_bag.png"),
                    Map.entry("glass", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/a65a012e-eglass.png"),
                    Map.entry("battery", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/697a4125-4lamp_battery.png"),
                    Map.entry("f_lamp", "https://bunri-wiki.s3.ap-northeast-2.amazonaws.com/697a4125-4lamp_battery.png")
            );
}
