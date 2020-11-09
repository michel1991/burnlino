package com.andrab.flares.cobolino;

import java.io.PrintStream;

class CliApp {
    private ForCalculatingPrices hex;

    private int precio;
    private int tasa;

    public CliApp(ForCalculatingPrices hex) {
        this.hex = hex;
    }

    public void iniciar(String... args) {
        this.args = args;
        parsear();
    }

    private void decir(String str) {
        stream.println(str);
    }

    public void setOut(PrintStream out) {
        stream = out;
    }

    private void parsear() {
        while (hayMas()) {
            if (donde == 1) {
                String functionName = args[donde];

                if (functionName.equals("echoValue")) {
                    precio = Integer.parseInt(args[++donde]);

                    decir(String.format("El precio es %.2f dólares", (float) hex.echoValue(precio)));
                } else if (functionName.equals("getTaxRateForState")) {
                    String state = args[++donde];
                    decir(String.format("La tasa de impuesto es %.2f por ciento.", hex.getTaxRateForState(state)));
                }
            }

            donde++;
        }
    }

    private boolean hayMas() {
        if (args.length <= donde)
            return false;
        if (donde >= args.length)
            return false;
        return true;
    }

    private static PrintStream stream = System.out;

    private String[] args;
    private int donde = 1;
}

public class Main {
    public static void main(String... args) {
        PriceMaker hex = new PriceMaker();
        hex.setTaxStore(new InMemoryTaxStore());

        CliApp ui = new CliApp(hex);
        ui.iniciar(args);
    }
}
