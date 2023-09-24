import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerCsvComponent } from './customer-csv.component';

describe('CustomerCsvComponent', () => {
  let component: CustomerCsvComponent;
  let fixture: ComponentFixture<CustomerCsvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerCsvComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerCsvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
