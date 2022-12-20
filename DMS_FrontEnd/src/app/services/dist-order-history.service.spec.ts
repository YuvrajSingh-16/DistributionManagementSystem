import { TestBed } from '@angular/core/testing';

import { DistOrderHistoryService } from './dist-order-history.service';

describe('DistOrderHistoryService', () => {
  let service: DistOrderHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DistOrderHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
