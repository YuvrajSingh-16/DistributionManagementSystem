import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistOrderHistoryComponent } from './dist-order-history.component';

describe('DistOrderHistoryComponent', () => {
  let component: DistOrderHistoryComponent;
  let fixture: ComponentFixture<DistOrderHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistOrderHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DistOrderHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
