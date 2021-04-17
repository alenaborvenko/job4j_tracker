package ru.job4j.inheritance;

public class ReportUsage {

    public static void main(String[] args) {
        TextReport report = new TextReport();
        String text = report.generate("Report's name", "Report's body");
        System.out.println(text);
        report = new HtmlReport();
        text = report.generate("Report's name", "Report's body");
        System.out.println(text);
        report = new JSONReport();
        text = report.generate("Report's name", "Report's body");
        System.out.println(text);
    }
}
