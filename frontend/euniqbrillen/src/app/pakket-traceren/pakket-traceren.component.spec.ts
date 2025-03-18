import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PakketTracerenComponent } from './pakket-traceren.component';

describe('PakketTracerenComponent', () => {
  let component: PakketTracerenComponent;
  let fixture: ComponentFixture<PakketTracerenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PakketTracerenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PakketTracerenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
