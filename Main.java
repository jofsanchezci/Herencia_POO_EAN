public class Main {
    public static void main(String[] args) {
        CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros(5000, 5);
        cuentaAhorros.consignar(1500);
        cuentaAhorros.retirar(200);
        cuentaAhorros.extractoMensual();
        cuentaAhorros.imprimir();

        CuentaCorriente cuentaCorriente = new CuentaCorriente(8000, 5);
        cuentaCorriente.consignar(2000);
        cuentaCorriente.retirar(9500);
        cuentaCorriente.extractoMensual();
        cuentaCorriente.imprimir();
    }
}