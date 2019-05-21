import {Trip} from './trip';

export class Booking {
  id: bigint;
  trip: Trip;
  customer: string;
  phoneNumber: string;
  tickets: bigint;
}
