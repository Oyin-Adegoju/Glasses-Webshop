import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfielComponent } from './profiel.component';

describe('ProfielComponent', () => {
  let component: ProfielComponent;
  let fixture: ComponentFixture<ProfielComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfielComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfielComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
