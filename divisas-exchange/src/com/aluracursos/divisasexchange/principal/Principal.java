package com.aluracursos.divisasexchange.principal;
import com.aluracursos.divisasexchange.calculos.MensajeUsuario;
import com.aluracursos.divisasexchange.calculos.RequestDatos;
import com.aluracursos.divisasexchange.calculos.WriteRequest;
import com.aluracursos.divisasexchange.exceptions.OpcionInvalidaException;
import com.aluracursos.divisasexchange.exceptions.SalidaProgramaException;
import com.aluracursos.divisasexchange.modelos.EntradaTipoDeCambio;
import com.aluracursos.divisasexchange.modelos.IngresoUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        MensajeUsuario datosMensaje = new MensajeUsuario();
        Scanner scanner = new Scanner(System.in);
        RequestDatos requestDatos = new RequestDatos();
        WriteRequest writer = new WriteRequest();
        ArrayList<IngresoUsuario> todosLosElementos = new ArrayList<>();
        datosMensaje.muestraMensajeUsuario();

        while (true) {
            try {
                IngresoUsuario modelo = new IngresoUsuario();

                datosMensaje.muestraMensajeUsuario("CAMBIAR");
                modelo.setDivisaActual(getDivisaDeUsuario(scanner, datosMensaje));

                datosMensaje.muestraMensajeUsuario("OBTENER");
                modelo.setNuevaDivisa(getDivisaDeUsuario(scanner, datosMensaje));


                EntradaTipoDeCambio datos = requestDatos.requestDatosDeCambio(modelo.getDivisaActual());

                if (datos != null && datos.conversion_rates() != null) {
                    float factor = datos.conversion_rates().get(modelo.getNuevaDivisa());
                    metodoConversion(factor, scanner, modelo);
                    todosLosElementos.add(modelo);
                } else {
                    System.out.println("Error al obtener los datos de cambio.");
                }
                if(!otrasOpciones(scanner, datosMensaje, writer, modelo, todosLosElementos)){
                    System.out.println("Gracias por usar nuestra App de DIVISAS EXCHANGE, vuelva pronto!!");
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error en la URL, verifique la dirección nuevamente por favor.");

            } catch (SalidaProgramaException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("ERROR, Ingrese por favor un valor válido.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getDivisaDeUsuario(Scanner scanner, MensajeUsuario datosMensaje) throws SalidaProgramaException {

        int seleccionarOpcion;
        String divisa = "";


        seleccionarOpcion = scanner.nextInt();
        if (seleccionarOpcion == 30) {
            throw new SalidaProgramaException("\nPrograma finalizado. Gracias por usar nuestra App de " +
                    "DIVISAS EXCHANGE, vuelva pronto!!");
        }

        try {
            verificaOpcionValida(seleccionarOpcion);
            divisa = getSolicitudActual(seleccionarOpcion);


        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("\nERROR!! Entrada inválida. Intente de nuevo...");
            // Vuelve a mostrar el mensaje de usuario para seleccionar la divisa
            datosMensaje.muestraMensajeUsuario("CAMBIAR");
            return getDivisaDeUsuario(scanner, datosMensaje);
        } catch (OpcionInvalidaException e) {
            System.out.println(e.getMessage());
            // Vuelve a mostrar el mensaje de usuario para seleccionar la divisa
            datosMensaje.muestraMensajeUsuario("CAMBIAR");
            return getDivisaDeUsuario(scanner, datosMensaje);
        }

        return divisa;


    }

    public static void verificaOpcionValida(int option) throws OpcionInvalidaException {
        if (option <= 0 || option > 30) {
            throw new OpcionInvalidaException("Opción inválida, intente de nuevo.");
        }

    }

    public static String getSolicitudActual(int seleccionarOpcion) {
        String divisa = "";


        switch (seleccionarOpcion) {
            case 1:
                divisa = "AUD";
                break;
            case 2:
                divisa = "BRL";
                break;
            case 3:
                divisa = "CAD";
                break;
            case 4:
                divisa = "CHF";
                break;
            case 5:
                divisa = "CNY";
                break;
            case 6:
                divisa = "DKK";
                break;
            case 7:
                divisa = "EUR";
                break;
            case 8:
                divisa = "GBP";
                break;
            case 9:
                divisa = "HKD";
                break;
            case 10:
                divisa = "INR";
                break;
            case 11:
                divisa = "JPY";
                break;
            case 12:
                divisa = "KRW";
                break;
            case 13:
                divisa = "LKR";
                break;
            case 14:
                divisa = "MXN";
                break;
            case 15:
                divisa = "MYR";
                break;
            case 16:
                divisa = "NOK";
                break;
            case 17:
                divisa = "NZD";
                break;
            case 18:
                divisa = "SEK";
                break;
            case 19:
                divisa = "SGD";
                break;
            case 20:
                divisa = "THB";
                break;
            case 21:
                divisa = "TWD";
                break;
            case 22:
                divisa = "USD";
                break;
            case 23:
                divisa = "ZAR";
                break;
            case 24:
                divisa = "ARS";
                break;
            case 25:
                divisa = "BOB";
                break;
            case 26:
                divisa = "CLP";
                break;
            case 27:
                divisa = "COP";
                break;
            case 28:
                divisa = "PAB";
                break;
            case 29:
                divisa = "PHP";
                break;
            default:
                System.out.println("ERROR!!");
                break;

        }
        return divisa;

    }

    private static void metodoConversion(float factorConversion, Scanner scanner, IngresoUsuario modelo) {
        float divisaCambio;
        float valorDeCambio;
        System.out.print("Ingrese el valor monetario que desea convertir: ");
        modelo.setValorDivisa(scanner.nextFloat());

        divisaCambio = modelo.getValorDivisa() * factorConversion;

        modelo.setNuevoValorDivisa((float) (Math.round(divisaCambio * 100.00) / 100.00));



        System.out.println("\n------------------------------------------------------------");
        System.out.println("El valor de cambio de " + modelo.getValorDivisa() + " " + modelo.getDivisaActual() + " a " +
                modelo.getNuevaDivisa() + " es de $ " + modelo.getNuevoValorDivisa() + " (" + modelo.getNuevaDivisa() +
                ").");
        System.out.println("------------------------------------------------------------");
    }

    private static boolean otrasOpciones(Scanner scanner, MensajeUsuario datosMensaje, WriteRequest writer,
                                         IngresoUsuario modelo,
                                         ArrayList<IngresoUsuario> todosLosElementos) throws IOException {

        int continuarConversion;

        System.out.println("\n¿Desea realizar alguna otra conversión? Digite el número de la opción que desea.");
        System.out.println("""
                        \s----------------------------------------------------------
                        1. SI.
                        \s----------------------------------------------------------
                        2. NO, deseo salir del programa sin imprimir los registros.
                        \s----------------------------------------------------------
                        3. Imprimir los registros que he consultado y luego SALIR.
                        \s----------------------------------------------------------
                        """);

        continuarConversion = scanner.nextInt();


        if (continuarConversion == 1) {
            datosMensaje.muestraMensajeUsuario();
            return true;

        } else if (continuarConversion == 2) {
            todosLosElementos.clear();
            return false;
        } else if (continuarConversion == 3) {
            escribirArchivo(writer,modelo, todosLosElementos);

            return false;
        } else {
            System.out.println("(Opción inválida, por favor intente de nuevo. ");
            return otrasOpciones(scanner, datosMensaje, writer, modelo, todosLosElementos);
        }
    }

    public static void escribirArchivo(WriteRequest writer, IngresoUsuario modelo,
                                       ArrayList<IngresoUsuario> allElements) throws IOException {

        try {
            writer.guardarJson(allElements);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
