import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeDistComponent } from './welcome-dist.component';

describe('WelcomeDistComponent', () => {
  let component: WelcomeDistComponent;
  let fixture: ComponentFixture<WelcomeDistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WelcomeDistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeDistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
