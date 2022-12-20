import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeRetailerComponent } from './welcome-retailer.component';

describe('WelcomeRetailerComponent', () => {
  let component: WelcomeRetailerComponent;
  let fixture: ComponentFixture<WelcomeRetailerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WelcomeRetailerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeRetailerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
