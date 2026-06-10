package com.example.evaluacion4charlottegabriel.ui;

public final class CardMeta {
    private CardMeta() {
    }

    public static String familyName(int id) {
        if (id < 22) return "Arcanos";
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
        if ("Chispas".equals(family)) return "Chispas · Comienzos";
        if ("Gotitas".equals(family)) return "Gotitas · Emociones";
        if ("Estrellas".equals(family)) return "Estrellas · Deseos";
        if ("Brotes".equals(family)) return "Brotes · Crecimiento";
        return "Arcanos · Destino";
    }

    public static String familyCaption(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return "valor, juego y comienzos brillantes";
        if ("Gotitas".equals(family)) return "emociones, ternura e intuicion";
        if ("Estrellas".equals(family)) return "deseos, guia y confianza";
        if ("Brotes".equals(family)) return "crecimiento, cuidado y abundancia";
        return "destino, cuento y magia mayor";
    }

    public static String familyMeaning(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) {
            return "La chispa inicial, inspiracion, energia pura y nuevas oportunidades.";
        }
        if ("Gotitas".equals(family)) {
            return "Nuevas emociones, intuicion, amor propio y sensibilidad.";
        }
        if ("Estrellas".equals(family)) {
            return "Deseos que guian el camino, confianza y luz interior.";
        }
        if ("Brotes".equals(family)) {
            return "Crecimiento suave, cuidado constante y abundancia paciente.";
        }
        return "Una pagina mayor del destino se abre con calma y magia.";
    }

    public static String rarity(int id) {
        if (id < 22) return "Legendaria";
        int rank = (id - 22) % 14;
        if (rank == 0 || rank >= 10) return "Especial";
        if (rank >= 7) return "Rara";
        return "Comun";
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
        return KawaiiSymbolView.STAR;
    }

    public static String story(String title, int id) {
        return title + " vive entre paginas suaves del album. Su brillo aparece cuando necesitas mirar una emocion con ternura y convertirla en un pequeno ritual de cuidado.";
    }
}
