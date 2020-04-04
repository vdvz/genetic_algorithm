package Entities;

public interface Bot_Interface {
/*
* makeDna - Бот создает Днк
* mutationDna - Бот мутирует свою днк
* die - Бот умирает очищая за собой ресурсы
* itter - Бот выполняет один цикл иттерации конкретного бота
* isAlive - возвращает true если бот жив
* */

    void set_x(int x);

    void set_y(int y);

    void makeDna();

    boolean isAlive();

    void mutationDna();

    String getDnaString();

    void die();

}
