import { TestBed } from '@angular/core/testing';

import { GetRetailerService } from './get-retailer.service';

describe('GetRetailerService', () => {
  let service: GetRetailerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetRetailerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
