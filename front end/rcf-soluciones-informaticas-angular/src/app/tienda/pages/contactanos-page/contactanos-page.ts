import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-contactanos-page',
  standalone: true,
  imports: [FormsModule, MatIconModule],
  templateUrl: './contactanos-page.html',
  styleUrl: './contactanos-page.css'
})
export class ContactanosPage {
  formData = {
    nombre: '',
    email: '',
    telefono: '',
    asunto: '',
    mensaje: ''
  };

  onSubmit() {
    if (!this.formData.nombre || !this.formData.email || !this.formData.mensaje) {
      alert('Por favor, completa todos los campos obligatorios.');
      return;
    }

    console.log('Formulario enviado:', this.formData);
    alert('¡Gracias por contactarnos! Te responderemos pronto.');

    
    this.formData = {
      nombre: '',
      email: '',
      telefono: '',
      asunto: '',
      mensaje: ''
    };
  }
}
