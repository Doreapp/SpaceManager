package com.mandin.antoine.spacemanager.v2;

public class Utils {
    public static final float
            KiloOctet = 1024,
            MegaOctet = KiloOctet * KiloOctet,
            GigaOctet = KiloOctet * MegaOctet,
            TeraOctet = KiloOctet * GigaOctet;

    public static final boolean debugging = true;


    public static float trim1(float val) {
        return ((int) val * 10) / 10f;
    }

    public static String formatSize(long size) {

        if (size < 0.1f * Utils.KiloOctet)
            return "0Ko";

        if (size < 10 * Utils.KiloOctet)
            return trim1((float) size / Utils.KiloOctet) + "Ko";

        if (size < Utils.MegaOctet)
            return (int) trim1((float) size / Utils.KiloOctet) + "Ko";


        if (size < 10 * Utils.MegaOctet)
            return trim1((float) size / Utils.MegaOctet) + "Mo";

        if (size < Utils.GigaOctet)
            return (int) trim1((float) size / Utils.MegaOctet) + "Mo";

        if (size < 10 * Utils.GigaOctet)
            return trim1((float) size / Utils.GigaOctet) + "Go";

        if (size < 100 * Utils.GigaOctet)
            return (int) trim1((float) size / Utils.GigaOctet) + "Go";

        return trim1((float) size / Utils.TeraOctet) + "To";
    }
}
