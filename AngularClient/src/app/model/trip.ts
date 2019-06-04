import {Landmark} from './landmark';
import {Company} from './company';

export class Trip {
  id: number;
  landmark: Landmark;
  transportCompany: Company;
  date: string;
  startTime: string;
  price: number;
  availablePlaces: bigint;
}
