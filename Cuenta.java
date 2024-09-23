class Cuenta {
    protected float saldo;
    protected int numeroDeConsignaciones;
    protected int numeroDeRetiros;
    protected float tasaAnual;
    protected float comisionMensual;

    public Cuenta(float saldoInicial, float tasaAnual) {
        this.saldo = saldoInicial;
        this.tasaAnual = tasaAnual;
        this.numeroDeConsignaciones = 0;
        this.numeroDeRetiros = 0;
        this.comisionMensual = 0; // Inicializa comisión mensual si es necesario
    }

    public void consignar(float cantidad) {
        this.saldo += cantidad;
        this.numeroDeConsignaciones++;
    }

    public void retirar(float cantidad) {
        if (cantidad <= this.saldo) {
            this.saldo -= cantidad;
            this.numeroDeRetiros++;
        } else {
            System.out.println("Fondos insuficientes para retirar.");
        }
    }

    public void calcularInteresMensual() {
        float interesMensual = (this.tasaAnual / 12) * this.saldo / 100;
        this.saldo += interesMensual;
    }

    public void extractoMensual() {
        calcularInteresMensual();
        this.saldo -= this.comisionMensual;
    }

    public void imprimir() {
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Comisión Mensual: " + this.comisionMensual);
        System.out.println("Número de Transacciones: " + (this.numeroDeConsignaciones + this.numeroDeRetiros));
    }
}

class CuentaDeAhorros extends Cuenta {
    private boolean activa;

    public CuentaDeAhorros(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        this.activa = saldoInicial < 10000; // Determina si la cuenta está activa basado en el saldo inicial
    }

    @Override
    public void consignar(float cantidad) {
        if (this.activa) {
            super.consignar(cantidad);
        }
    }

    @Override
    public void retirar(float cantidad) {
        if (this.activa) {
            super.retirar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        super.calcularInteresMensual();
        if (this.numeroDeRetiros > 4) {
            this.saldo -= 1000; // Comisión adicional por más de 4 retiros
        }
        this.saldo -= this.comisionMensual;
        this.activa = this.saldo < 10000; // Actualiza el estado activo de la cuenta
    }
}

class CuentaCorriente extends Cuenta {
    private float sobregiro;

    public CuentaCorriente(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        this.sobregiro = 0; // Inicializar el sobregiro
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= this.saldo + this.sobregiro) {
            this.saldo -= cantidad;
            if (this.saldo < 0) {
                this.sobregiro += this.saldo;
                this.saldo = 0;
            }
        } else {
            System.out.println("Fondos insuficientes, incluyendo sobregiro.");
        }
    }

    @Override
    public void consignar(float cantidad) {
        if (this.sobregiro > 0) {
            float sobrante = cantidad - this.sobregiro;
            this.sobregiro = Math.max(0, this.sobregiro - cantidad);
            if (sobrante > 0) {
                super.consignar(sobrante);
            }
        } else {
            super.consignar(cantidad);
        }
    }
}


