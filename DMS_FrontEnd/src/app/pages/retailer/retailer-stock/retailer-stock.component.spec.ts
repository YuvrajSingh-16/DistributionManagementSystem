import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailerStockComponent } from './retailer-stock.component';

describe('RetailerStockComponent', () => {
  let component: RetailerStockComponent;
  let fixture: ComponentFixture<RetailerStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RetailerStockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailerStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
