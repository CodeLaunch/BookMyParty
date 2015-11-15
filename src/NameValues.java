
public class NameValues
{
    protected static final int step = 20;
    protected int size = 20;
    protected int cur_size = 0;
    protected String[] names;
    protected Object[] values;

    public NameValues() {
        names = new String[size];
        values = new Object[size];
    }

    public NameValues(int init_size) {
        names = new String[init_size];
        values = new Object[init_size];
        size = init_size;
    }

    public NameValues(NameValues nvs) {
        names = nvs.getNames();
        values = nvs.getValues();
        size = names.length;
        cur_size = size;
    }

    public void clear() {
        size = 20;
        cur_size = 0;
        names = new String[size];
        values = new Object[size];
    }

    public void put(String name, Object value) {
        if (cur_size >= size) {
            size += step;
            String[] names1 = new String[size];
            Object[] values1 = new Object[size];

            for (int i=0; i<cur_size; i++) {
                names1[i] = names[i];
                values1[i] = values[i];
            }

            names = names1;
            values = values1;
        }

        names[cur_size] = name;
        values[cur_size] = value;
        cur_size++;
    }

    public int size() {
        return cur_size;
    }

    public String getName(int index) {
        return names[index];
    }

    public Object getValue(int index) {
        return values[index];
    }

    public String[] getNames() {
        String[] names1 = new String[cur_size];

        for (int i=0; i<cur_size; i++) {
            names1[i] = names[i];
        }

        return names1;
    }

    public Object[] getValues() {
        Object[] values1 = new String[cur_size];

        for (int i=0; i<cur_size; i++) {
            values1[i] = values[i];
        }

        return values1;
    }

    public Object getObject(String name) {
        for (int i=0; i<cur_size; i++) {
            if (names[i].equalsIgnoreCase(name))
                return values[i];
        }

        return null;
    }

    public Object get(String name) {
        return getObject(name);
    }

    public String getString(String name) {
        try {
            for (int i=0; i<cur_size; i++) {
                if (names[i].equalsIgnoreCase(name))
                    return values[i].toString();
            }
        } catch (Exception e) {}

        return null;
    }

    public String getStringLike(String name) {
        try {
            for (int i=0; i<cur_size; i++) {
                if (names[i].indexOf(name) != -1)
                    return values[i].toString();
            }
        } catch (Exception e) {}

        return null;
    }
    public int getInt(String name) {
        try {
            for (int i=0; i<cur_size; i++) {
                if (names[i].equalsIgnoreCase(name))
                    return Integer.parseInt(values[i].toString());
            }
        } catch (Exception e) {}

        return 0;
    }

    public long getLong(String name) {
        try {
            for (int i=0; i<cur_size; i++) {
                if (names[i].equalsIgnoreCase(name))
                    return Long.parseLong(values[i].toString());
            }
        } catch (Exception e) {}

        return 0;
    }

    public double getDouble(String name) {
        try {
            for (int i=0; i<cur_size; i++) {
                if (names[i].equalsIgnoreCase(name))
                    return Double.parseDouble(values[i].toString());
            }
        } catch (Exception e) {}

        return 0;
    }

    public void set(String name, Object value) {
        for (int i=0; i<cur_size; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                values[i] = value;
                return;
            }
        }
        put(name, value);
    }

    public void replace(String name, Object value) {
        for (int i=0; i<cur_size; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                values[i] = value;
                break;
            }
        }
    }

    public void replace(int index, String name, Object value) {
        if (name != null && value != null) {
            names[index] = name;
            values[index] = value;
        }
    }

    public String toString() {
        String str = "";

        for (int i=0; i<cur_size; i++) {
            if (i != 0) {
                str += ", ";
            }
            str += names[i] + "=" + values[i].toString();
        }
        return str;
    }
}

