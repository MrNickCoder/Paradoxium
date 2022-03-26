package com.ncoder.paradoxium.enums;

import com.ncoder.paradoxlib.utils.SkullTexture;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public enum PresentType {
    WHITE_WOOL("White", 4, "10f5398510b1a05afc5b201ead8bfc583e57d7202f5193b0b761fcbd0ae2"),
    ORANGE_WOOL("Orange", 4, "ee3a8fd0852977444d9fd7797cac07b8d3948addc43f0bb5ce25ae72d95dc"),
    MAGENTA_WOOL("Magenta", 4, "47e55fcc809a2ac1861da2a67f7f31bd7237887d162eca1eda526a7512a64910"),
    LIGHT_BLUE_WOOL("Light Blue", 4, "84e1c42f11383b9dc8e67f2846fa311b16320f2c2ec7e175538dbff1dd94bb7"),
    YELLOW_WOOL("Yellow", 4, "a3e58ea7f3113caecd2b3a6f27af53b9cc9cfed7b043ba334b5168f1391d9"),
    LIME_WOOL("Lime", 4, "c0fcab834e3e7c0d6af5dc5972f7b9aab8c73285035bd0075eedb74f352e086"),
    PINK_WOOL("Pink", 4, "10c75a05b344ea043863974c180ba817aea68678cbea5e4ba395f74d4803d1d"),
    GRAY_WOOL("Gray", 4, "5f2b3f7cdb59394a0c82fe67e672842d755ce8e8eddaeeb9b5cc173b5501e8d6"),
    LIGHT_GRAY_WOOL("Light Gray", 4, "ac3821d4f61b17f82f0d7a8e5312608ff50ede29b1b4dc89847be9427d36"),
    CYAN_WOOL("Cyan", 4, "59f0743576bba4a2622480548970b721543d2c457955e8dd5c4f9ddb6a56b95c"),
    PURPLE_WOOL("Purple", 4, "1b6730de7e5b941efc6e8cbaf5755f9421a20de871759682cd888cc4a81282"),
    BLUE_WOOL("Blue", 4, "1c6274c22d726fc120ce25736030cc8af238b44bcbf56655207953c414422f"),
    BROWN_WOOL("Brown", 4, "ca75328eba7cb5a052326f59ddaf67a1cebd4e55bb6801c03c3142e28e1"),
    GREEN_WOOL("Green", 4, "ed97f4f44e796f79ca43097faa7b4fe91c445c76e5c26a5ad794f5e479837"),
    RED_WOOL("Red", 4, "6cef9aa14e884773eac134a4ee8972063f466de678363cf7b1a21a85b7"),
    BLACK_WOOL("Black", 4, "5c712b1971c5f42eeff80551179220c08b8213eacbe6bc19d238c13f86e2c0"),
    NETHERITE_BLOCK("Netherite", 1, "be0c7af591bd1337944abfac52b9bcf883528db72aacb6dbc4edd56a803667f4"),
    EMERALD_BLOCK("Emerald", 1, "509e5e444291e6837157002ee9d5f604c697039eec7acba2478fb09684f98ff7"),
    DIAMOND_BLOCK("Diamond", 1, "9dd8673f842e50f131b377c84b6fe51c219106df3fbc091a7bc9269b91c66052"),
    GOLD_BLOCK("Gold", 2, "bcaad86c708eb27773a64f93479e39f0442a5ce086b63296c7b7d1cf51160956"),
    COPPER_BLOCK("Copper", 2, "74300a5f8111753fb9e224d60e46d46fbb03cf3f0974b3dee43887ea5ff01fcb"),
    IRON_BLOCK("Iron", 2, "4025dd135b2e82e8cfd205cb27473f476735e8232b8238018cf15d34193024c1"),
    STONE("Stone", 4, "b06a3de2b1b95f42ffd448018d1cc11dc763f9d5c722cc1b6cc3fcfd4f09089e"),
    OAK_PLANKS("Oak", 4, "5028da6289d37e9432666b8f2d08d824852e75211dab7f87223e89845b42d9e2");

    private final String name;
    private final int amount;
    private final SkullTexture texture;

    @ParametersAreNonnullByDefault
    PresentType(String name, int amount, String texture) {
        this.name = name;
        this.amount = amount;
        this.texture = new SkullTexture(texture);
    }

    public static PresentType getByMaterial(Material material) {
        return PresentType.valueOf(material.name());
    }

    @Nonnull
    public String getName() { return name; }

    @Nonnull
    public int getAmount() { return amount; }

    @Nonnull
    public SkullTexture getTexture() { return texture; }
}
