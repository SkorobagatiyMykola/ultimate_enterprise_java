package ua.skorobahatyi.case4;

public class DemoCase4 {
    public static void main(String[] args) {
        var context = new CustomContext();
        var printer = context.getBean(HelloPrinter.class);
        printer.printHello();

        var call = context.getBean(User4.class);
        call.call("Nick");
    }
}
