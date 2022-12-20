import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetRetailerComponent } from './get-retailer.component';

describe('GetRetailerComponent', () => {
  let component: GetRetailerComponent;
  let fixture: ComponentFixture<GetRetailerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetRetailerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetRetailerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
