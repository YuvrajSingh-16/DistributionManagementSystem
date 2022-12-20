import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDistributorComponent } from './get-distributor.component';

describe('GetDistributorComponent', () => {
  let component: GetDistributorComponent;
  let fixture: ComponentFixture<GetDistributorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetDistributorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetDistributorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
