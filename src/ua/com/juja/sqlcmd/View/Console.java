package ua.com.juja.sqlcmd.View;

import java.util.Scanner;

public class Console implements View {
    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
