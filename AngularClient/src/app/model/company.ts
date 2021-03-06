import {CompanyType} from './company-type.enum';

export class Company {
  id: number;
  name: string;
  address: string;
  email: string;
  phoneNumber: string;
  description: string;
  companyType: CompanyType;
}
