package org.academiadecodigo.bootcamp;

public class Machine {

    //parametrizar o método em vez da classe
    <T> T operate(T t, MonoOperation<T> operation) {
        return operation.operate(t);
    }

    <T> T operate(T t1, T t2, BiOperation<T> operation) {
        return operation.operate(t1, t2);
    }

    //Dizer que vamos trabalhar com dois genéricos, um p retorno e um
    //para argumentos
    <T, E> T confirm(BiOperationTwoTypes<T, E> biOperation, E element1, E element2) {
        return biOperation.execute(element1, element2);
    }

    <T, E, R> R doesThisWorkForReal(T elem1, E elem2, BiOperationThreeTypes<T, E, R> operation) {
        return operation.doesThisWork(elem1, elem2);
    }
}
