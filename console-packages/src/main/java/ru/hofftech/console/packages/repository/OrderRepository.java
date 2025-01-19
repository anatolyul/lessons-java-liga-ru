package ru.hofftech.console.packages.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.hofftech.console.packages.model.Order;
import ru.hofftech.console.packages.model.Truck;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Repository
public class OrderRepository {
    private final BoxRepository boxRepository;
    private List<Order> orders = new ArrayList<>();

    public OrderRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;

        List<Truck> trucks = List.of(
                new Truck("truck 1", boxRepository.getBoxesRandom(5)),
                new Truck("truck 2", boxRepository.getBoxesRandom(5)),
                new Truck("truck 3", boxRepository.getBoxesRandom(5)),
                new Truck("truck 4", boxRepository.getBoxesRandom(5)),
                new Truck("truck 5", boxRepository.getBoxesRandom(5)),
                new Truck("truck 6", boxRepository.getBoxesRandom(5))
        );

        orders.addAll(List.of(
                new Order(1, "Petrov",
                        LocalDate.of(2025, 1, 11),
                        LocalDate.of(2025, 1, 11), trucks),
                new Order(2, "Petrov",
                        LocalDate.of(2025, 1, 11),
                        LocalDate.of(2025, 1, 12), trucks),
                new Order(3, "Petrov",
                        LocalDate.of(2025, 1, 12),
                        LocalDate.of(2025, 1, 12), trucks),
                new Order(4, "Ivanov",
                        LocalDate.of(2025, 1, 11),
                        LocalDate.of(2025, 1, 11), trucks),
                new Order(5, "Ivanov",
                        LocalDate.of(2025, 1, 12),
                        LocalDate.of(2025, 1, 12), trucks),
                new Order(6, "Ivanov",
                        LocalDate.of(2025, 1, 13),
                        LocalDate.of(2025, 1, 13), trucks)
        ));
    }
}
