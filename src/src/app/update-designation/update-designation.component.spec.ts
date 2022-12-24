import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDesignationComponent } from './update-designation.component';

describe('UpdateDesignationComponent', () => {
  let component: UpdateDesignationComponent;
  let fixture: ComponentFixture<UpdateDesignationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDesignationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateDesignationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
