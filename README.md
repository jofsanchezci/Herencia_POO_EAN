
# Herencia en Java

## Concepto de Herencia
La herencia es un principio fundamental en la programación orientada a objetos que permite a una clase derivar o heredar propiedades y métodos de otra clase. La clase que hereda se llama subclase o clase hija, y la clase de la que hereda se llama superclase o clase padre. La herencia facilita la reutilización de código y la eficiencia en la organización del mismo.


##  Extensión
La herencia es un principio fundamental de la programación orientada a objetos (OOP) que permite a una clase derivar propiedades y métodos de otra clase. Esta relación modela un comportamiento "es un tipo de" entre dos clases, donde la clase derivada (subclase) hereda características de la clase base (superclase). Este mecanismo promueve la reutilización de código y la creación de una jerarquía de clases más fácil de manejar y mantener. A continuación, te explico algunos aspectos clave de la herencia:

## Ventajas de la Herencia
- **Reutilización de Código.** Permite a los desarrolladores reutilizar código de clases existentes, lo que ahorra tiempo y reduce errores al no tener que reescribir código existente.
- **Jerarquía Clara.** Organiza y define clases en una jerarquía que refleja relaciones del mundo real, facilitando la comprensión del diseño del software.
- **Extensibilidad.** Las clases se pueden extender para modificar o añadir funcionalidades, lo que facilita la actualización y mantenimiento de sistemas.
- **Polimorfismo** La herencia es una condición previa para el polimorfismo, donde una interfaz puede ser implementada por múltiples clases de formas diferentes.

## Tipos de Herencia
- **Herencia Simple.** Una clase deriva de una sola clase base. Es el tipo de herencia más común y está soportada por la mayoría de los lenguajes de programación orientados a objetos, como Java y C#.
- **Herencia Múltiple** Una clase puede heredar de más de una clase base. Esto es soportado por algunos lenguajes como Python y C++, pero no por Java o C# directamente.

- **Herencia Multinivel.** Es una forma de herencia donde una clase se deriva de otra clase derivada, formando una "cadena" de herencia.


## Implementación en Java
En Java, la herencia se implementa con la palabra clave extends. Por ejemplo, si tienes una clase Vehículo y quieres crear una clase Coche que herede de Vehículo, lo harías de la siguiente manera:
```java
class Vehiculo {
    protected int velocidad;

    public void acelerar() {
        velocidad += 10;
    }
}

class Coche extends Vehiculo {
    private int puertas;

    public void cerrarPuertas() {
        System.out.println("Puertas cerradas");
    }
}

```
En este ejemplo, Coche hereda velocidad y acelerar() de Vehiculo, y añade su propia propiedad puertas y el método cerrarPuertas().

## Consideraciones
- **Sobrecarga y Sobreescritura.** La herencia permite a las clases derivadas sobrecargar los métodos de la clase base (crear métodos con el mismo nombre pero diferentes parámetros) o sobreescibirlos (redefinir completamente el método en la subclase).
- **Constructores y Herencia.** Los constructores no se heredan, pero el constructor de la clase base puede ser invocado desde la clase derivada.
- La herencia es una herramienta poderosa, pero debe usarse con precaución para evitar la creación de jerarquías complejas que puedan hacer el código difícil de entender y mantener. Se recomienda utilizar la composición sobre la herencia en casos donde no se necesita una relación estricta de tipo "es un tipo de".

## Implementación del Ejercicio de Cuenta Bancaria
Este ejercicio modela un sistema de cuentas bancarias utilizando herencia para compartir comportamientos comunes y permitir especializaciones en subclases. Desarrollar un programa que modele una cuenta bancaria que tiene los siguientes atributos, que deben ser de acceso protegido:
- Saldo, de tipo float.
- Número de consignaciones con valor inicial cero, de tipo int.
- Número de retiros con valor inicial cero, de tipo int.
- Tasa anual (porcentaje), de tipo float.
- Comisión mensual con valor inicial cero, de tipo float.
- La clase Cuenta tiene un constructor que inicializa los atributos saldo y tasa anual con valores pasados como parámetros. La clase 

## Métodos
Cuenta tiene los siguientes métodos:

- Consignar una cantidad de dinero en la cuenta actualizando su saldo.
- Retirar una cantidad de dinero en la cuenta actualizando su saldo. El valor a retirar no debe superar el saldo.
- Calcular el interés mensual de la cuenta y actualiza el saldo correspondiente.
- Extracto mensual: actualiza el saldo restando la comisión mensual y calculando el interés mensual correspondiente (invoca el método anterior).
- Imprimir: muestra en pantalla los valores de los atributos.

## Clases Hijas

La clase Cuenta tiene dos clases hijas:

- Cuenta de ahorros: posee un atributo para determinar si la cuenta de ahorros está activa (tipo boolean). Si el saldo es menor a $10,000, la cuenta está inactiva, en caso contrario se considera activa. 
**Los siguientes métodos se redefinen:**
- Consignar: se puede consignar dinero si la cuenta está activa. Debe invocar al método heredado.
- Retirar: es posible retirar dinero si la cuenta está activa. Debe invocar al método heredado.
- Extracto mensual: si el número de retiros es mayor que 4, por cada retiro adicional, se cobra $1000 como comisión mensual. Al generar el extracto, se determina si la cuenta está activa o no con el saldo.

- Cuenta corriente: posee un atributo de sobregiro, el cual se inicializa en cero. 
**Se redefinen los siguientes métodos:**
- Retirar: se retira dinero de la cuenta actualizando su saldo. Se puede retirar dinero superior al saldo. El dinero que se debe queda como sobregiro.
- Consignar: invoca al método heredado. Si hay sobregiro, la cantidad consignada reduce el sobregiro.
- Extracto mensual: invoca al método heredado.
- Un nuevo método imprimir que muestra en pantalla el saldo de la cuenta, la comisión mensual, el número de transacciones realizadas (suma de cantidad de consignaciones y retiros) y el valor de sobregiro.


### Clase Base `Cuenta`
```java
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
        this.comisionMensual = 0;
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
```

### Subclases `CuentaDeAhorros` y `CuentaCorriente`
```java
class CuentaDeAhorros extends Cuenta {
    private boolean activa;

    public CuentaDeAhorros(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        this.activa = saldoInicial < 10000;
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
            this.saldo -= 1000;
        }
        this.saldo -= this.comisionMensual;
        this.activa = this.saldo < 10000;
    }
}

class CuentaCorriente extends Cuenta {
    private float sobregiro;

    public CuentaCorriente(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        this.sobregiro = 0;
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
```

### Método `main` para Pruebas
```java
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
```
