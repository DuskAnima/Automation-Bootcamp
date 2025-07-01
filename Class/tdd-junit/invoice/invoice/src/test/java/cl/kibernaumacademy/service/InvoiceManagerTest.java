package cl.kibernaumacademy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.kibernaumacademy.models.Invoice;
import cl.kibernaumacademy.models.InvoiceManager;
import static org.assertj.core.api.Assertions.*;

public class InvoiceManagerTest {
    private InvoiceManager manager; // Necesito un servicio

    @BeforeEach
    void setUp() {
        manager = new InvoiceManager();
    }

    @Test
    void shouldCreateAnInvoice() {
        Invoice inv = manager.createInvoice("Tocomples", 1500.0); // El servicio se vale de un modelo
        assertThat(inv.getId()).isEqualTo(1);
        assertThat(manager.getAll()).hasSize(1);  // llama directamente al servicio
    }
    
    @Test
    void shouldUpdateInvoice() {
        Invoice inv = manager.createInvoice("TOCOMPLES", 1500.0);
        boolean updated = manager.updateStatus(inv.getId(), "PAGADA"); // Los estados de las facturas se pueden pagar
        assertThat(updated).isTrue();
        assertThat(inv.getStatus()).isEqualTo("");
    }

    @Test
    void shouldInvoice() {
        Invoice inv  = manager.createInvoice("TECHNOVA", 1500.0);
        boolean removed = manager.deleteInvoice(inv.getId());
        assertThat(removed).isTrue();
        assertThat(manager.getAll()).isEmpty();
  }

}
