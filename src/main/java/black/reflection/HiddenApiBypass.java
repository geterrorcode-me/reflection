package black.reflection;

import java.lang.reflect.Method;

public class HiddenApiBypass {
    
    public static boolean bypass() {
        try {
            // Gunakan refleksi tingkat tinggi untuk mencari class
            Method forName = Class.class.getDeclaredMethod("forName", String.class);
            Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);

            // 1. Ambil class VMRuntime
            Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
            
            // 2. Ambil method getRuntime (Koreksi di sini: jangan di-cast ke Class)
            Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", (Object) null);
            
            // 3. Ambil instance runtime
            Object runtime = getRuntime.invoke(null);
            
            // 4. Ambil method setHiddenApiExemptions
            Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Object[]{new Class[]{String[].class}});
            
            // 5. Eksekusi bypass
            setHiddenApiExemptions.invoke(runtime, new Object[]{new String[]{"L"}});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
