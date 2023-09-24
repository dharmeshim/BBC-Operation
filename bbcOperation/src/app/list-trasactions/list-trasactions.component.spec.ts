import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTrasactionsComponent } from './list-trasactions.component';

describe('ListTrasactionsComponent', () => {
  let component: ListTrasactionsComponent;
  let fixture: ComponentFixture<ListTrasactionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListTrasactionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTrasactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
