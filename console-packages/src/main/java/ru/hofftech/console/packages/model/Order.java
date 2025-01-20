package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Модель заказа, содержащая информацию о загрузке и разгрузке коробок.
 */
@Data
@AllArgsConstructor
public class Order {
    /**
     * Идентификатор заказа.
     */
    private int orderId;

    /**
     * Идентификатор пользователя.
     */
    private String userId;

    /**
     * Дата загрузки.
     */
    private LocalDate dateLoad;

    /**
     * Дата разгрузки.
     */
    private LocalDate dateUnload;

    /**
     * Список грузовиков, участвующих в заказе.
     */
    private List<Truck> trucks;

    /**
     * Возвращает список всех коробок, загруженных в грузовики.
     *
     * @return список коробок
     */
    public List<Box> getBoxes() {
        return getTrucks().stream()
                .filter(truck -> truck.getBoxes() != null)
                .flatMap(truck -> truck.getBoxes().stream())
                .toList();
    }
}
