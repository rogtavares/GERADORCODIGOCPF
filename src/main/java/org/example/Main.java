
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(gerarCpf("sp"));

    }
    //GERAR UMA LISTA COM 8 NÚMEROS ALEATÓRIOS
    static List<Integer> gerarNumerosAleatorios(List<Integer> lista, int inicio, int fim, int intervalo) {
        Random aleatorio = new Random();

        for(int i = inicio; i < fim; i++) {
            int numero = aleatorio.nextInt(intervalo);
            lista.add(numero);
        }
        return lista;
    }
    //CAPTURAR A INFORMAÇÃO DA REGIÃO FISCAL
    static int obterRegiaoFiscal(String entrada) {

        String regiao = entrada.toUpperCase();
        int regiaoFiscal = -1;

        switch(regiao) {
            case "DF", "GO", "MS", "MT", "TO":
                regiaoFiscal = 1;
                break;
            case "AC", "AM", "AP", "PA", "RO", "RR":
                regiaoFiscal = 2;
                break;
            case "CE", "MA", "PI":
                regiaoFiscal = 3;
                break;
            case "AL", "PB", "PE", "RN":
                regiaoFiscal = 4;
                break;
            case "BA", "SE" :
                regiaoFiscal = 5;
                break;
            case "MG":
                regiaoFiscal = 6;
                break;
            case "ES", "RJ":
                regiaoFiscal = 7;
                break;
            case "SP":
                regiaoFiscal = 8;
                break;
            case "PR", "SC":
                regiaoFiscal = 9;
                break;
            case "RS":
                regiaoFiscal = 0;
                break;
            default:
                System.out.println("Digite uma região fiscal válida!");
        }

        return regiaoFiscal;
    }
    //ADICIONAR UM ELEMENTO A LISTA
    static List<Integer> adicionarNumero(List<Integer> lista, int elemento) {
        lista.add(elemento);
        return lista;
    }
    //GERAR O PRIMEIRO DIGITO VERIFICADOR
    static int primeiroDigitoVerficador(List<Integer> fator1, int[] fator2) {
        int soma = 0;
        for(int i = 0; i < 9; i++) {
            soma = (fator1.get(i) * fator2[i]) + soma;
        }
        int resto = (soma % 11);
        int digito;

        if(resto == 0 || resto == 1) {
            digito = 0;
        }
        else {
            digito = 11 - resto;
        }
        return digito;
    }

    //GERAR O SEGUNDO DIGITO VERIFICADO
    static int segundoDigitoVerificador(List<Integer> fator1, int[] fator2) {
        int soma = 0;
        for(int i = 0; i < 9; i++) {
            soma = (fator1.get(i + 1) * fator2[i]) + soma;
        }
        int resto = (soma % 11);
        int digito;

        if(resto == 0 || resto == 1) {
            digito = 0;
        }
        else {
            digito = 11 - resto;
        }
        return digito;
    }

    // GERAR CPF VÁLIDO
    static String gerarCpf(String reg) {

        List<Integer> numerosAleatorios = new ArrayList<>();
        int[] numeros = {10, 9, 8, 7, 6, 5, 4, 3, 2};

        gerarNumerosAleatorios(numerosAleatorios, 0, 8, 10);
        adicionarNumero(numerosAleatorios, obterRegiaoFiscal(reg));
        primeiroDigitoVerficador(numerosAleatorios, numeros);
        adicionarNumero(numerosAleatorios, primeiroDigitoVerficador(numerosAleatorios, numeros));
        segundoDigitoVerificador(numerosAleatorios, numeros);
        adicionarNumero(numerosAleatorios, segundoDigitoVerificador(numerosAleatorios, numeros));

        return numerosAleatorios.toString().replaceAll("\\[|\\]", "").replaceAll(",", "");

    }
}