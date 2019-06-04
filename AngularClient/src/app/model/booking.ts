import {Trip} from './trip';

export class Booking {
  id: number;
  trip: Trip;
  customer: string;
  phoneNumber: string;
  tickets: bigint;
}
