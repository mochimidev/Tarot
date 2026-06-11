package com.example.evaluacion4charlottegabriel.ui;

public final class CardMeta {
    private CardMeta() {
    }

    public static String familyName(int id) {
        if (id < 22) return "Unicornios";
        if (id < 36) return "Chispas";
        if (id < 50) return "Gotitas";
        if (id < 64) return "Estrellas";
        return "Brotes";
    }

    public static String cardTitle(int id) {
        String[] arcana = {
                "El Loco", "El Mago", "La Sacerdotisa", "La Emperatriz", "El Emperador",
                "Los Enamorados", "El Carro", "La Fuerza", "El Ermitano",
                "La Rueda de la Fortuna", "La Justicia", "El Colgado", "La Muerte",
                "La Templanza", "El Diablo", "La Torre", "La Estrella", "La Luna",
                "El Sol", "El Juicio", "El Mundo", "Reverso de Carta"
        };
        if (id < 22) return arcana[Math.max(0, Math.min(id, arcana.length - 1))];
        String[] ranks = {"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho",
                "Nueve", "Diez", "Aprendiz", "Explorador", "Reina", "Guardian"};
        return ranks[(id - 22) % 14] + " de " + familyName(id);
    }

    public static String familyTag(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return "Chispas \u00b7 Comienzos";
        if ("Gotitas".equals(family)) return "Gotitas \u00b7 Emociones";
        if ("Estrellas".equals(family)) return "Estrellas \u00b7 Deseos";
        if ("Brotes".equals(family)) return "Brotes \u00b7 Crecimiento";
        return "Unicornios \u00b7 Destino";
    }

    public static String familyCaption(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return "valor, juego y comienzos brillantes";
        if ("Gotitas".equals(family)) return "emociones, ternura e intuici\u00f3n";
        if ("Estrellas".equals(family)) return "deseos, gu\u00eda y confianza";
        if ("Brotes".equals(family)) return "crecimiento, cuidado y abundancia";
        return "destino, cuento y magia mayor";
    }

    public static String familyMeaning(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) {
            return "La chispa inicial, inspiraci\u00f3n, energ\u00eda pura y nuevas oportunidades.";
        }
        if ("Gotitas".equals(family)) {
            return "Nuevas emociones, intuici\u00f3n, amor propio y sensibilidad.";
        }
        if ("Estrellas".equals(family)) {
            return "Deseos que gu\u00edan el camino, confianza y luz interior.";
        }
        if ("Brotes".equals(family)) {
            return "Crecimiento suave, cuidado constante y abundancia paciente.";
        }
        return "Una p\u00e1gina mayor del destino se abre con calma y magia.";
    }

    public static String rarity(int id) {
        if (id < 22) return "Legendaria";
        int rank = (id - 22) % 14;
        if (rank == 0 || rank >= 10) return "Especial";
        if (rank >= 7) return "Rara";
        return "Com\u00fan";
    }

    public static int familyColor(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return DreamColors.FLAME;
        if ("Gotitas".equals(family)) return DreamColors.DROP;
        if ("Estrellas".equals(family)) return DreamColors.STAR;
        if ("Brotes".equals(family)) return DreamColors.SPROUT;
        return DreamColors.LILAC;
    }

    public static int familySymbol(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return KawaiiSymbolView.FLAME;
        if ("Gotitas".equals(family)) return KawaiiSymbolView.DROP;
        if ("Estrellas".equals(family)) return KawaiiSymbolView.STAR;
        if ("Brotes".equals(family)) return KawaiiSymbolView.SPROUT;
        return KawaiiSymbolView.HEART;
    }

    public static String story(String title, int id) {
        return title + " vive entre p\u00e1ginas suaves del \u00e1lbum. Su brillo aparece cuando necesitas mirar una emoci\u00f3n con ternura y convertirla en un peque\u00f1o ritual de cuidado.";
    }
}
