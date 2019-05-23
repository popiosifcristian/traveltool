import {Landmark} from './landmark';
import {Company} from './company';

export class Trip {
  id: bigint;
  landmark: Landmark;
  transportCompany: Company;
  date: string;
  startTime: string;
  price: number;
  availablePlaces: bigint;
}
