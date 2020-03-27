package Entities;

import org.jetbrains.annotations.NotNull;

public interface worldObject {
    /*
    Тип объекта - целое неотрицательное число.
    compareType - возвращает true если Типы объектов совпадают, в противном случае false

    Integer type = null;

    static boolean compareType(@NotNull worldObject obj) {
        return getType().equals(obj.getType());
    }

    */
    Integer getType();
}
