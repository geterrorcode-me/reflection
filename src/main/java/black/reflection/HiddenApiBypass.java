package black.reflection;

import java.lang.reflect.Method;

public class HiddenApiBypass {
    
    /**
     * Membuka blokir Hidden API agar kita bisa menggunakan 
     * semua class di framework.jar tanpa batasan.
     */
    public static boolean bypass() {
        try {
            // Teknik khusus menggunakan VMRuntime untuk 'unseal' sistem
            Method forName = Class.class.getDeclaredMethod("forName", String.class);
            Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);

            Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
            Method getRuntime = (Class<?>) vmRuntimeClass.getDeclaredMethod("getRuntime");
            Object runtime = getRuntime.invoke(null);
            
            Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            
            // Memberikan pengecualian pada semua package (L)
            setHiddenApiExemptions.invoke(runtime, new Object[]{new String[]{"L"}});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
