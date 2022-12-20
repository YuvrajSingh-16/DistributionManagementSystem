import { TestBed } from '@angular/core/testing';

import { DistributordetailsService } from './distributordetails.service';

describe('DistributordetailsService', () => {
  let service: DistributordetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DistributordetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
