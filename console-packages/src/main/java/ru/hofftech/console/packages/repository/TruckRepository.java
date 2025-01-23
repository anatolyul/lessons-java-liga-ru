package ru.hofftech.console.packages.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.hofftech.console.packages.model.Truck;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Repository
public class TruckRepository {
    private List<Truck> trucks = new ArrayList<>();

    /**
     * Получает список грузовиков на основе аргументов команды.
     *
     * @return список грузовиков
     */
    public List<Truck> createTrucksByForm(String trucksForms) {
        if (trucksForms != null && !trucksForms.isEmpty()) {
            String[] truckDimensions = trucksForms.replace("n", "\n")
                    .replace("\\n", "\n").split("\n");
            for (String dimension : truckDimensions) {
                String[] sizes = dimension.split("x");
                Truck truck = new Truck("Truck " + dimension,
                        Integer.parseInt(sizes[0]),
                        Integer.parseInt(sizes[1]));
                trucks.add(truck);
            }
        }
        return trucks;
    }
}
