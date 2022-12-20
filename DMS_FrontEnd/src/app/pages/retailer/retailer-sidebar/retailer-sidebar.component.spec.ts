import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailerSidebarComponent } from './retailer-sidebar.component';

describe('RetailerSidebarComponent', () => {
  let component: RetailerSidebarComponent;
  let fixture: ComponentFixture<RetailerSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RetailerSidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailerSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
