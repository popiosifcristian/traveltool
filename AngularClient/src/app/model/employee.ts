import {Company} from "./company";

export class Employee {
  id: bigint;
  username: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  agency: Company;
}
