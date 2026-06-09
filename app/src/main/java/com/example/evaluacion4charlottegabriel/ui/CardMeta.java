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

    public static String familyCaption(int id) {
        String family = familyName(id);
        if ("Chispas".equals(family)) return "valor, juego y comienzos brillantes";
        if ("Gotitas".equals(family)) return "emociones, ternura e intuicion";
        if ("Estrellas".equals(family)) return "deseos, guia y confianza";
        if ("Brotes".equals(family)) return "crecimiento, cuidado y abundancia";
        return "destino, cuento y magia mayor";
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
        return KawaiiSymbolView.UNICORN;
    }

    public static String story(String title, int id) {
        return title + " vive entre paginas suaves del album. Su brillo aparece cuando necesitas mirar una emocion con ternura y convertirla en un pequeno ritual de cuidado.";
    }
}
