import {Landmark} from "./landmark";
import {Company} from "./company";

export class Trip {
  id: bigint;
  landmark: Landmark;
  transportCompany: Company;
  date: LocalDate;
  startTime: LocalTime;
  price: number;
  availablePlaces: bigint;
}
