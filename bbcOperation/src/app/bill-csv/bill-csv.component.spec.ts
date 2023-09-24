import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillCsvComponent } from './bill-csv.component';

describe('BillCsvComponent', () => {
  let component: BillCsvComponent;
  let fixture: ComponentFixture<BillCsvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillCsvComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BillCsvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
